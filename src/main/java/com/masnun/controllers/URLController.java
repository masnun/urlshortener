package com.masnun.controllers;

import com.masnun.domain.URL;
import com.masnun.repository.URLRepository;
import com.masnun.utils.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@Controller
public class URLController {

    @Autowired
    private URLRepository urlRepository;

    @Value("${app.url}")
    private String applicationURL;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("baseUrl", applicationURL);
        return "index";
    }

    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> shorten(@RequestParam String url) {
        URL urlObj = new URL();
        urlObj.setUrl(url);
        urlObj.setCreated_at(new Date());

        urlRepository.save(urlObj);

        Long urlId = urlObj.getId();

        String shortURL = applicationURL + "/r" + Base62.fromBase10(urlId);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", shortURL);

        return hashMap; // ;

    }


    @RequestMapping("/r{path}")
    public String redirect(@PathVariable String path) {
        Long urlId = Base62.toBase10(path);
        URL urlObj = urlRepository.findOne(urlId);

        return "redirect:" + urlObj.getUrl();
    }
}
