package org.example.backend.cryptocurrency.cryptocurrency.service;

import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.PlatformMapper;
import org.example.backend.cryptocurrency.cryptocurrency.repository.PlatformRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    private final PlatformRepository platformRepository;

    public TokenService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public Optional<List<PlatformDto>> getAllTokens(Long startIndex, Long lastIndex) {

        List<Platform> platformList = new ArrayList<>();
        for (Long currentIndex=startIndex;currentIndex<=lastIndex;currentIndex++) {
            Optional<Platform> currentPlatform = platformRepository.findById(currentIndex);
            currentPlatform.ifPresent(platformList::add);
        }

        List<PlatformDto> platformDtoList = platformList.stream()
                .map(PlatformMapper::toDto).toList();

        return Optional.of(platformDtoList);
    }
}
