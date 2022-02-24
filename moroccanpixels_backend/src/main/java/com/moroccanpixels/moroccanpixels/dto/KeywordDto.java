package com.moroccanpixels.moroccanpixels.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDto {
    private Long id;
    private String name;
    private Set<String> images;
}
