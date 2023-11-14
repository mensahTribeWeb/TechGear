package com.example.demo.service;

import com.example.demo.domain.AboutInfo;
import com.example.demo.repositories.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImpl implements AboutService {
    private final AboutRepository aboutRepository;

    @Autowired
    public AboutServiceImpl(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    @Override
    public AboutInfo getAboutInfo() {
        // Assuming there's an entry with ID 1 in the database, otherwise provide defaults
        return aboutRepository.findById(1L).orElseGet(this::createDefaultAboutInfo);
    }

    private AboutInfo createDefaultAboutInfo() {
        AboutInfo defaultAboutInfo = new AboutInfo();
        defaultAboutInfo.setMissionStatement("\"At TechGear Builders, our mission is to empower individuals, whether they are hobbyists or professionals, who aspire to build, customize, and upgrade their own computers, laptops, mobile devices, tablets, and monitors. We are dedicated to providing high-quality components and materials that fuel innovation and creativity, enabling our customers to unleash their full potential in the world of technology.\"");
        defaultAboutInfo.setAboutParagraph("\"Welcome to TechGear Builders, your one-stop destination for all your tech-building needs. Our company is built on a foundation of passion for technology and a commitment to serving tech enthusiasts of all levels. Whether you're a seasoned pro looking for specialized parts or a beginner exploring the exciting world of tech assembly, we have you covered.\n" +
                "\n" +
                "At TechGear Builders, we understand that the heart of every great tech project lies in the quality of its components. That's why we meticulously source and offer a wide range of top-notch parts and materials, handpicked to ensure superior performance, reliability, and compatibility.\n" +
                "\n" +
                "Our user-friendly platform makes it easy to find the right components, compare products, and access valuable resources. We are not just a store; we are a community of tech enthusiasts, ready to share knowledge, provide support, and assist you on your tech-building journey.\n" +
                "\n" +
                "With TechGear Builders, you're not just buying components; you're investing in your tech dreams. Join us today, and let's build the future together.\"");
        return defaultAboutInfo;
    }
}
