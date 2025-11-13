package com.devpulse.devpulse.controller;


import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 총지배인, 웹 브라우저의 외부 요청을 받고, HTML 화면을 담당
public class PulseController {


    private final PulseRepository pulseRepository; // 데이터 목록을 보여줘야 하기 때문에 Repository 필요

    public PulseController(PulseRepository pulseRepository) { // 생성자 주입 -> final로 선언한것은 생성자 의존성으로 주입해야함, 필드 주입x
        this.pulseRepository = pulseRepository;
    }

    @GetMapping("/report") // /report라는 주소로 get방식의 요청이 들어왔을때만 실행
    public String reportMapping(Model model) {
        List<DataEntity> dataList = pulseRepository.findAll();
        model.addAttribute("pulses", dataList); // dataList를 pulses이름표를 붙어서 model에 전달
        return "report";
    }


}
