package com.devpulse.devpulse.Controller;


import com.devpulse.devpulse.dto.DataDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PulseController {

    @GetMapping("/report")
    public String reportMapping(Model model) {
        List<DataDTO> dataList = new ArrayList<>();

        DataDTO data1 = new DataDTO();
        data1.setBlogName("블로그");
        data1.setCommitNum(5);
        data1.setDate("2025-11-01");

        DataDTO data2 = new DataDTO();
        data2.setBlogName("티스토리");
        data2.setCommitNum(1);

        dataList.add(data1);
        dataList.add(data2);

        model.addAttribute("pulse", dataList);

        return "report";
    }


}
