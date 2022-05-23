package com.Netex.Reader.Gui.controller;

import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.models.UserFileDto;
import com.Netex.Reader.Gui.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryContoller {
    private final HistoryService historyService;

    @GetMapping
    public List<UserFileDto> getHistory() {
        return historyService.getHistory();
    }

}
