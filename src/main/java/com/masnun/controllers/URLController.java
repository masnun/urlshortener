package com.masnun.controllers;

import com.masnun.domain.URL;
import com.masnun.repository.URLRepository;
import com.masnun.utils.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class URLController {

    @Autowired
    URLRepository urlRepository;

    @RequestMapping("/")
    public String index() {
        return "Home";
    }

    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    public
    @ResponseBody
    String shorten(@RequestParam String url) {
        URL urlObj = new URL();
        urlObj.setUrl(url);
        urlObj.setCreated_at(new Date());

        urlRepository.save(urlObj);

        Long urlId = urlObj.getId();

        return Base62.fromBase10(urlId);

    }


    @RequestMapping("/r{path}")
    public String redirect(@PathVariable String path) {
        Long urlId = Base62.toBase10(path);
        URL urlObj = urlRepository.findOne(urlId);

        return "redirect:" + urlObj.getUrl();
    }
}
