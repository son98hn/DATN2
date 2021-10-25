package com.example.datn.Controller.User.API;

import com.example.datn.Utils.Utility;
import com.example.datn.dto.UserDTO;
import com.example.datn.entity.UserEntity;
import com.example.datn.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author: Pham Ngoc Son
 * <p>
 * Oct 23, 2021
 */
@RestController
public class MailAPI {
    private final
    IUserService userService;

    private final
    JavaMailSender mailSender;

    public MailAPI(IUserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @PostMapping(value = "/resetPassword")
    public void resetPassword(@RequestBody UserDTO userDTO, Model model) {
        if(userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            UserEntity userEntity = userService.getResetPasswordToken(userDTO.getToken());
            if(userEntity == null) {
                model.addAttribute("message","Invalid token");
            } else {
                userService.updatePassword(userEntity, userDTO.getPassword());
                model.addAttribute("message","Đổi mật khẩu thành công!");
            }
        } else {
            model.addAttribute("message","Mật khẩu không khớp!");
        }
    }

    public void sendEmail(String email, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("son98hn@gmail.com","DATN");
        helper.setTo(email);
        String subject = "Quên mật khẩu";
        helper.setSubject(subject);
        String content = "<p>Ấn vào đây để đổi mật khẩu:</p>"+
                "<p><a href=\""+link+"\">Đổi mật khẩu</a></p>";
        helper.setText(content, true);
        mailSender.send(message);
    }

    @PostMapping(value = "/forgotPassword")
    public void sendNewPassword(Model model, @RequestBody UserDTO userDTO, HttpServletRequest request) {
        String token = RandomString.make(30);
        try {
            userService.updateResetPasswordToken(token, userDTO.getEmail());
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword?token=" + token;
            sendEmail(userDTO.getEmail(), resetPasswordLink);
            model.addAttribute("message", "Mật khẩu mới đã được gửi vào email của bạn!");
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("message","Lỗi khi gửi mail!");
        }
    }
}
