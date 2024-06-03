package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.services.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DatabaseController {
    @Autowired
    private BackupService backupService;
    @GetMapping ("/backupDatabase")
    public String backupDatabase(RedirectAttributes redirectAttributes) throws InterruptedException, IOException {
        String backupFileName = "backup_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".sql";
        String backupFilePath = "src/main/resources/backups/" + backupFileName;

        try {
            String result= backupService.backupDatabase("root", "calde0102", "viajefeliz", backupFilePath );
            redirectAttributes.addFlashAttribute("message", result);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Ocurrió un error: " + e.getMessage());
            return "redirect:/backupResult";
        }
        return "redirect:/backupResult";


    }

    @GetMapping("/backupResult")
    public String backupResult() {
        return "backup/backupResult";
    }
}
