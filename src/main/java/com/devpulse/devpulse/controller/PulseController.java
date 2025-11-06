package com.devpulse.devpulse.controller;


import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PulseController {

    @Autowired
    private final PulseRepository pulseRepository;

    public PulseController(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    @GetMapping("/report")
    public String reportMapping(Model model) {
        List<DataEntity> dataList = pulseRepository.findAll();
        model.addAttribute("pulses", dataList);
        return "report";
    }


}
