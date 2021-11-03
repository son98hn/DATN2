package com.example.datn.service.impl;

import com.example.datn.dto.NewDTO;
import com.example.datn.entity.CategoryEntity;
import com.example.datn.entity.NewEntity;
import com.example.datn.repository.CategoryRepository;
import com.example.datn.repository.CommentRepository;
import com.example.datn.repository.NewRepository;
import com.example.datn.service.INewService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {
    private final NewRepository newRepository;

    private final CategoryRepository categoryRepository;

    private final CommentRepository commentRepository;

    public NewService(NewRepository newRepository, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.newRepository = newRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveNew(NewDTO newDTO) {
        if (newDTO.getId() != null) {
            NewEntity oldNewEntity = newRepository.findById(newDTO.getId()).get();
            if (!verifyUpdateNew(oldNewEntity, newDTO) && verifyContent(newDTO.getContent()) && verifyTitle(newDTO.getTitle())) {
                newDTO.setMessage("Bài viết đã tồn tại");
            } else {
                oldNewEntity.setTag(newDTO.getTag());
                oldNewEntity.setTitle(newDTO.getTitle());
                oldNewEntity.setContent(newDTO.getContent());
                oldNewEntity.setThumbnail(newDTO.getThumbnail());
                oldNewEntity.setShortDescription(newDTO.getShortDescription());
                CategoryEntity categoryEntity = categoryRepository.findById(newDTO.getCategoryId()).get();
                oldNewEntity.setCategoryEntity(categoryEntity);
                newRepository.save(oldNewEntity);
                newDTO.setMessage("Cập nhật bài viết thành công");
            }
        } else {
            NewEntity newEntity = new NewEntity();
            if (!verifyContent(newDTO.getContent()) && !verifyTitle(newDTO.getTitle())) {
                newEntity.setCreatedBy(newDTO.getCreatedBy());
                newEntity.setCreatedDate(LocalDateTime.now());
                newEntity.setTitle(newDTO.getTitle());
                newEntity.setContent(newDTO.getContent());
                newEntity.setTag(newDTO.getTag());
                newEntity.setThumbnail(newDTO.getThumbnail());
                newEntity.setStatus(0);
                newEntity.setViews(0L);
                newEntity.setShortDescription(newDTO.getShortDescription());
                CategoryEntity categoryEntity = categoryRepository.findById(newDTO.getCategoryId()).get();
                newEntity.setCategoryEntity(categoryEntity);
                newRepository.save(newEntity);
                newDTO.setMessage("Thêm bài viết thành công");
            } else {
                newDTO.setMessage("Bài viết đã tồn tại");
            }
        }
    }

    private Boolean verifyContent(String content) {
        return newRepository.existsByContent(content);
    }

    private Boolean verifyTitle(String title) {
        return newRepository.existsByTitle(title);
    }

    private Boolean verifyUpdateNew(NewEntity newEntity, NewDTO newDTO) {
        return newEntity.getContent().equals(newDTO.getContent()) && newEntity.getTitle().equals(newDTO.getTitle());
    }

    @Override
    public int totalItemActive() {
        return (int) newRepository.totalItemActive();
    }

    @Override
    public int totalItemDeactive() {
        return (int) newRepository.totalItemDeactive();
    }

    @Override
    public NewEntity findById(Long id) {
        return newRepository.findById(id).get();
    }

    @Override
    public List<NewEntity> findNewsByCategoryParentCode(String categoryParentCode, Pageable pageable) {
        return newRepository.findNewsByCategoryParentCode(categoryParentCode, pageable);
    }

    @Override
    public List<NewEntity> findNewsByCategoryParentCode1(String categoryParentCode) {
        return newRepository.findNewsByCategoryParentCode1(categoryParentCode);
    }

    @Override
    public List<NewEntity> findAllActive(Pageable pageable) {
        return newRepository.findAllActive(pageable);
    }

    @Override
    public List<NewEntity> findAllDeactive(Pageable pageable) {
        return newRepository.findAllDeactive(pageable);
    }

    @Override
    public int totalItemByCategoryParent(String categoryParent) {
        return (int) newRepository.totalItemByCategoryParent(categoryParent);
    }

    @Override
    public int totalItemByCategory(Long categoryId) {
        return newRepository.totalItemByCategory(categoryId);
    }

    @Override
    public NewEntity findTopByCategoryParentCode(String categoryParentCode) {
        return newRepository.findTopByCategoryParentCode(categoryParentCode);
    }

    @Override
    public NewEntity findByTitle(String title) {
        return newRepository.findByTitle(title);
    }

    @Override
    public List<NewEntity> findByCreatedBy(String username, Pageable pageable) {
        return newRepository.findByCreatedBy(username, pageable);
    }

    @Override
    public int coutAllByCreatedBy(String username) {
        return (int) newRepository.countAllByCreatedBy(username);
    }

    @Override
    public List<NewEntity> findTop3ByTag(String tag) {
        return newRepository.findTop3ByTag(tag);
    }

    @Override
    public List<NewEntity> findByCategoryEntityId(Long categoryId) {
        return newRepository.findByCategoryEntityId(categoryId);
    }

    @Override
    public List<NewEntity> findByCategoryEntityIdPage(Long categoryId, Pageable pageable) {
        return newRepository.findByCategoryEntityIdPage(categoryId, pageable);
    }

    @Override
    public List<NewEntity> search(String title, Pageable pageable) {
        return newRepository.search(title, pageable);
    }

    @Override
    public int countSearch(String title) {
        return newRepository.countSearch(title);
    }

    @Override
    public List<NewEntity> findTop5ByViewsDesc() {
        return newRepository.findTop5ByViewsDesc();
    }

    @Override
    public void autoCreateChinhtri() throws IOException {
        Document document = null;
        String link = "https://zingnews.vn/chinh-tri.html";
        String zingnews = "https://zingnews.vn";
        List<String> listname = new ArrayList<>();
        List<String> listavatar = new ArrayList<>();
        List<String> listtomtat = new ArrayList<>();
        List<String> listcontent = new ArrayList<>();

        document = Jsoup
                .connect(link)
                .userAgent("Jsoup client")
                .timeout(20000).get();
        Elements title = document.select("p[class=article-title] a");


        document = Jsoup
                .connect(link)
                .userAgent("Jsoup client")
                .timeout(20000).get();
        Elements thumbnail = document.select("p[class=article-thumbnail] a img");

        document = Jsoup
                .connect(link)
                .userAgent("Jsoup client")
                .timeout(20000).get();
        Elements shortDescription = document.select("p[class=article-summary]");

        for (Element element : title) {
            listname.add(element.html());
            String href = element.attr("href");
            document = Jsoup
                    .connect(zingnews + href)
                    .timeout(20000).get();
            Elements noidung = document.select("div[class=the-article-body] p");
//            listcontent.add(String.valueOf(noidung));
            Elements imgs = document.select("td[class=pic] img");
            for (Element img : imgs) {
                img.attr("src", String.valueOf(img.attr("data-src")));
            }
            listcontent.add(String.valueOf(noidung) + String.valueOf(imgs));
        }

        for (Element element : thumbnail) {
            if (String.valueOf(element.attr("src")).startsWith("https")) {
                listavatar.add(String.valueOf(element.attr("src")));
            } else {
                listavatar.add(String.valueOf(element.attr("data-src")));
            }
        }

        for (Element element : shortDescription) {
            listtomtat.add(element.html());
        }

        for (int i = 0; i < 5; i++) {
            NewEntity newEntity = new NewEntity();
            CategoryEntity categoryEntity = categoryRepository.findByCode("chinh-tri");
            newEntity.setCategoryEntity(categoryEntity);
            newEntity.setShortDescription(listtomtat.get(i));
            newEntity.setThumbnail(listavatar.get(i));
            newEntity.setTitle(listname.get(i));
            newEntity.setCreatedBy("admin");
            newEntity.setCreatedDate(LocalDateTime.now());
            newEntity.setStatus(0);
            newEntity.setViews(0L);
            newEntity.setContent(listcontent.get(i));
            newRepository.save(newEntity);
//            int sl =dangKyRepository.findAllByCategoryId(1L).size();
//            if(sl >0){
//                for(int j=0; j< sl;j++){
//                    sendMailService.Sendmail(accountRepository.findFirstByUserName(dangKyRepository.findAllByCategoryId(1L).get(j).getUserId()).getEmail());
//                }
//            }
        }
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            commentRepository.deleteAllByNewEntityId(item);
            newRepository.removeById(item);
        }
    }
}
