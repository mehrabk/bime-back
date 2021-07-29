package com.project.bime.service.ghest;

import com.project.bime.model.Bime;
import com.project.bime.model.Ghest;
import com.project.bime.repository.GhestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class GhestInitService {
    private GhestRepository ghestRepository;
    @Autowired
    public GhestInitService(GhestRepository ghestRepository) {
        this.ghestRepository = ghestRepository;
    }

    public Bime ghestInit(Bime savedNewBime, int ghestCount) {
        long ghestPerMonth = (savedNewBime.getTotalPrice() - savedNewBime.getPishPardakht()) / ghestCount;
        List<Ghest> ghestList = new ArrayList<>();
        for (int i = 1; i <= ghestCount; i++) {
            Ghest ghest = new Ghest();
            ghest.setGhestNumber(i);
            ghest.setGhestPrice(ghestPerMonth);
            ghest.setGhestDate(addMonth(savedNewBime.getContractDate(), i));
            ghest.setBime(savedNewBime);
            ghestList.add(ghestRepository.save(ghest));
        }
        savedNewBime.setGhestList(ghestList);
        return savedNewBime;
    }

    public static Date addMonth(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, i);
        return c.getTime();
    }
}
