package com.joalvarez.example.data.dto.generals;

import com.joalvarez.example.constants.IResponse;

import java.util.List;

public record ResponseDTO (int code, String message, List<String> details) implements BaseDTO {}