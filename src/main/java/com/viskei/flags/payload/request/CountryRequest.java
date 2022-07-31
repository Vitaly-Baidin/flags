package com.viskei.flags.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CountryRequest {

    @NotEmpty(message = "Input countries list cannot be empty.")
    private List<String> countries;

    @NotEmpty(message = "Path cannot be empty.")
    private String path;

    @Pattern(regexp = "((?i)svg\\b)|((?i)png\\b)", message = "image format must be svg or png")
    private String imageFormat;

}
