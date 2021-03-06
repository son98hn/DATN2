package com.example.datn.Controller.User;

import com.example.datn.dto.UserDTO;
import com.example.datn.entity.*;
import com.example.datn.repository.GroupRepository;
import com.example.datn.repository.NewRepository;
import com.example.datn.service.ICategoryParentService;
import com.example.datn.service.ICategoryService;
import com.example.datn.service.ICommentService;
import com.example.datn.service.INewService;
import com.example.datn.service.impl.FunctionService;
import com.example.datn.service.impl.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    private final ICategoryService categoryService;

    private final ICategoryParentService categoryParentService;

    private final INewService newService;

    private final UserService userService;

    private final GroupRepository groupRepository;

    private final ICommentService commentService;

    private final FunctionService functionService;

    private final JavaMailSender mailSender;

    private final NewRepository newRepository;

    public HomeController(ICategoryService categoryService, ICategoryParentService categoryParentService, INewService newService, UserService userService, GroupRepository groupRepository, ICommentService commentService, FunctionService functionService, JavaMailSender mailSender, NewRepository newRepository) {
        this.categoryService = categoryService;
        this.categoryParentService = categoryParentService;
        this.newService = newService;
        this.userService = userService;
        this.groupRepository = groupRepository;
        this.commentService = commentService;
        this.functionService = functionService;
        this.mailSender = mailSender;
        this.newRepository = newRepository;
    }

    @GetMapping(value = {"/trang-chu"})
    public String index(Model model, HttpServletRequest request, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                        @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort, Principal principal) {
        model.addAttribute("categoryParent", categoryParentService.findAll());
//        page
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sortable);
        model.addAttribute("totalPage", (int) Math.ceil((double) newService.totalItemActive() / size));
        model.addAttribute("page", page);
        model.addAttribute("lastNews", newService.findAllActive(pageable));
        model.addAttribute("sportNews", newService.findNewsByCategoryParentCode1("the-thao"));
        model.addAttribute("technologyNews", newService.findNewsByCategoryParentCode1("cong-nghe"));
        model.addAttribute("firstSportNew", newService.findTopByCategoryParentCode("the-thao"));
        model.addAttribute("firstSocietyNew", newService.findTopByCategoryParentCode("xa-hoi"));
        model.addAttribute("firstWorldNew", newService.findTopByCategoryParentCode("the-gioi"));
        model.addAttribute("firstTechnologyNew", newService.findTopByCategoryParentCode("cong-nghe"));
        model.addAttribute("firstEntertainmentNew", newService.findTopByCategoryParentCode("giai-tri"));
//       b??i vi???t ph??? bi???n
        model.addAttribute("popularNews", newService.findTop5ByViewsDesc());
//
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        return "web/home";
    }

    @GetMapping(value = "/bai-viet/{id}")
    public String baiViet(Model model, Principal principal,
                          @PathVariable("id") Long id) {
        NewEntity newEntity = newService.findById(id);
        model.addAttribute("bv", newEntity);
        model.addAttribute("categoryParent", categoryParentService.findAll());
//        model.addAttribute("comments", commentService.findAllByNewEntityId(id));
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            UserEntity userEntity = userService.findByUserName(principal.getName());
            model.addAttribute("USERMODEL", userEntity);
            List<String> list_functionCode_str = new ArrayList<>();
            List<FunctionEntity> functionEntities = functionService.findFunctionByUserName(userEntity.getUsername());
            for (FunctionEntity functionEntity : functionEntities) {
                String functionCode_str = functionEntity.getCode();
                list_functionCode_str.add(functionCode_str);
            }
            model.addAttribute("userfunction", list_functionCode_str);
        }
        List<UserEntity> userEntityList = userService.findByNew(id);
        model.addAttribute("listUser", userEntityList);
        List<CommentEntity> commentEntityList = commentService.findAllByNewEntityId(id);
        model.addAttribute("comments", commentEntityList);
//      b??i vi???t li??n quan
        List<NewEntity> listNewTag = newService.findTop3ByTag(newEntity.getTag());
        List<NewEntity> listNewCategories = newService.findByCategoryEntityId(newEntity.getCategoryEntity().getId());
        listNewCategories.remove(newEntity);
        listNewCategories.removeAll(listNewTag);
        listNewTag.remove(newEntity);
        listNewTag.addAll(listNewCategories);
        model.addAttribute("listNewTag", listNewTag);
//
//      t??ng l?????t view
        newEntity.setViews(newEntity.getViews() + 1);
        newRepository.save(newEntity);
        return "web/bai-viet";
    }

    @GetMapping(value = "/profile")
    public String profile(Model model, Principal principal) {
        UserEntity userEntity = userService.findByUserName(principal.getName());
        model.addAttribute("user", userEntity);
        model.addAttribute("categoryParent", categoryParentService.findAll());
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        return "web/profile";
    }

    @GetMapping(value = "/nhom-bai-viet/{categoryParent}")
    public String nhomBaiViet(Model model, Principal principal,
                              @PathVariable("categoryParent") String categoryParent,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "8") Integer size,
                              @RequestParam(value = "sort", required = false, defaultValue = "DESC") String sort) {
//       page
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sortable);
        model.addAttribute("totalPage", (int) Math.ceil((double) newService.totalItemByCategoryParent(categoryParent) / size));
        model.addAttribute("page", page);
        model.addAttribute("listNews", newService.findNewsByCategoryParentCode(categoryParent, pageable));
        model.addAttribute("categoryParent", categoryParentService.findAll());
        model.addAttribute("nameCategory", categoryParentService.findByCode(categoryParent).getName());
        model.addAttribute("codeCategory", categoryParent);
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        //       b??i vi???t ph??? bi???n
        model.addAttribute("popularNews", newService.findTop5ByViewsDesc());
        return "web/nhom-bai-viet";
    }

    //    @GetMapping(value = "/403")
//    public String accessDenied(Model model, Principal principal) {
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        String userInfo= WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//        String message = principal.getName()+"you don't have permission to access this page!";
//        model.addAttribute("message", message);
//        return "web/403";
//    }

    @GetMapping(value = "/login")
    public String loginPage(Model model,
                            @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("categoryParent", categoryParentService.findAll());
        if (Objects.equals(message, "fail")) {
            model.addAttribute("message", "T??n t??i kho???n ho???c m???t kh???u kh??ng ????ng!");
            model.addAttribute("alert", "danger");
        } else if (Objects.equals(message, "register_success")) {
            model.addAttribute("message", "T???o t??i kho???n th??nh c??ng!");
            model.addAttribute("alert", "success");
        }
        return "web/login";
    }

    @GetMapping(value = "/userNew")
    public String listNew(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "8") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestParam(value = "message", required = false) String message, Model model, Principal principal) {
        UserEntity user = userService.findByUserName(principal.getName());
//        Page
        model.addAttribute("totalPage", (int) Math.ceil((double) newService.coutAllByCreatedBy(user.getUsername()) / size));
        model.addAttribute("page", page);
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sortable);
//
//        message
        if (Objects.equals(message, "update_success")) {
            model.addAttribute("message", "C???p nh???t th??nh c??ng!");
            model.addAttribute("alert", "success");
        } else if (Objects.equals(message, "update_error")) {
            model.addAttribute("message", "C???p nh???t th???t b???i!");
            model.addAttribute("alert", "danger");
        } else if (Objects.equals(message, "delete_success")) {
            model.addAttribute("message", "X??a th??nh c??ng!");
            model.addAttribute("alert", "success");
        } else if (Objects.equals(message, "delete_error")) {
            model.addAttribute("message", "X??a th???t b???i!");
            model.addAttribute("alert", "danger");
        } else if (Objects.equals(message, "insert_success")) {
            model.addAttribute("message", "T???o th??nh c??ng!");
            model.addAttribute("alert", "success");
        } else if (Objects.equals(message, "insert_error")) {
            model.addAttribute("message", "T???o th???t b???i!");
            model.addAttribute("alert", "danger");
        }
//
        model.addAttribute("listNew", newService.findByCreatedBy(user.getUsername(), pageable));
        model.addAttribute("categoryParent", categoryParentService.findAll());
        List<String> listcategory_str = new ArrayList<>();
        List<NewEntity> newEntities = newService.findByCreatedBy(user.getUsername(), pageable);
        for (NewEntity entity : newEntities) {
            Long categoryId = entity.getCategoryEntity().getId();
            CategoryEntity categoryDTO = categoryService.findById(categoryId);
            listcategory_str.add(categoryDTO.getName());
        }
        model.addAttribute("listCategory", listcategory_str);
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        return "web/userNew";
    }

    @GetMapping(value = "/addOrUpdateNew")
    public String addOrUpdateNew(@RequestParam(value = "id", required = false) Long id, Model model, HttpServletRequest request, Principal principal) {
        NewEntity newEntity = new NewEntity();
        if (id != null) {
            newEntity = newService.findById(id);
        }
        List<CategoryEntity> categoryDTOList = categoryService.findAll();
        model.addAttribute("categories", categoryDTOList);
        model.addAttribute("categoryParent", categoryParentService.findAll());
        model.addAttribute("news", newEntity);
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        return "web/addOrUpdateNew";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword(Model model, Principal principal,
                                 @RequestParam(value = "message", required = false) String message) {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        if (Objects.equals(message, "insert_success")) {
            model.addAttribute("message", "M???t kh???u m???i ???? ???????c g???i v??o mail c???a b???n!");
            model.addAttribute("alert", "success");
        }
        model.addAttribute("categoryParent", categoryParentService.findAll());
        return "web/forgotPassword";
    }

    @GetMapping(value = "/resetPasswordEmail")
    public String resetPasswordForm(@RequestParam(value = "token", required = false) String token, Model model, Principal principal,
                                    @RequestParam(value = "message", required = false) String message) {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        if (Objects.equals(message, "insert_success")) {
            model.addAttribute("message", "Thay ?????i m???t kh???u th??nh c??ng!");
            model.addAttribute("alert", "success");
        }
        model.addAttribute("categoryParent", categoryParentService.findAll());
        UserEntity userEntity = userService.getResetPasswordToken(token);
        model.addAttribute("token", token);
        if (userEntity == null) {
            model.addAttribute("message", "Token kh??ng h???p l???!");
            model.addAttribute("alert", "danger");
        }
        return "web/resetPasswordEmail";
    }

    @GetMapping(value = "/resetPassword")
    public String resetPassword(
            @RequestParam(value = "message", required = false) String message,
            Model model, Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("USERMODEL", userService.findByUserName(principal.getName()));
        }
        if (Objects.equals(message, "insert_success")) {
            model.addAttribute("message", "Thay ?????i m???t kh???u th??nh c??ng!");
            model.addAttribute("alert", "success");
        }
        model.addAttribute("categoryParent", categoryParentService.findAll());
        return "web/resetPassword";
    }

    @GetMapping(value = "/register")
    public String register(Model model, @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("categoryParent", categoryParentService.findAll());
        if (Objects.equals(message, "insert_success")) {
            model.addAttribute("message", "????ng k?? th??nh c??ng!");
            model.addAttribute("alert", "success");
        }
        return "web/register";
    }
}
