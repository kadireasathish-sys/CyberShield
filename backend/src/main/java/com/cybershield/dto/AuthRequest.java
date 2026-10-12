package com.cybershield.dto; import jakarta.validation.constraints.*; public record AuthRequest(@Email @NotBlank String email,@NotBlank String password){}
