package com.sangdaero.walab.interest.application.service;


import com.sangdaero.walab.interest.application.DTO.InterestDTO;
import com.sangdaero.walab.interest.domain.entity.InterestCategory;
import com.sangdaero.walab.interest.domain.repository.InterestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterestService {

    private InterestRepository mInterestRepository;

    public InterestService(InterestRepository interestRepository) {
        this.mInterestRepository = interestRepository;
    }

    public Long addInterest(InterestDTO interestDto) {
        return mInterestRepository.save(interestDto.toEntity()).getId();
    }

    public List<InterestDTO> getInterestList() {
        List<InterestCategory> interestCategories = mInterestRepository.findAll();
//        List<InterestCategory> interestCategories = mInterestRepository.findByTypeEquals(type);
        List<InterestDTO> interestDTOList = new ArrayList<>();

        for(InterestCategory interestCategory: interestCategories) {
            InterestDTO interestDTO = InterestDTO.builder()
                    .id(interestCategory.getId())
                    .name(interestCategory.getName())
                    .type(interestCategory.getType())
                    .on_off(interestCategory.getOn_off())
                    .createdDate(interestCategory.getCreatedDate())
                    .modifiedDate(interestCategory.getModifiedDate())
                    .build();

            interestDTOList.add(interestDTO);
        }
        return interestDTOList;
    }

    public InterestDTO getInterest(Long id) {
        Optional<InterestCategory> interestWrapper = mInterestRepository.findById(id);
        InterestCategory interestCategory = interestWrapper.get();

        InterestDTO interestDTO = InterestDTO.builder()
                .id(interestCategory.getId())
                .name(interestCategory.getName())
                .type(interestCategory.getType())
                .on_off(interestCategory.getOn_off())
                .build();

        return interestDTO;
    }

    public void deleteInterest(Long id) {
        mInterestRepository.deleteById(id);
    }
}
