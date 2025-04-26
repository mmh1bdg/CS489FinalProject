package com.radix.usermanagement.controller;

import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;
import com.radix.usermanagement.dto.response.StandardResponse;
import com.radix.usermanagement.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PutMapping("/{userId}")
    public ResponseEntity<StandardResponse<CreditCardResponseDTO>> updateCreditCard(
            @PathVariable String userId,
            @RequestBody @Valid CreditCardUpdateRequestDTO requestDTO
    ) {
        CreditCardResponseDTO updatedCard = creditCardService.updateCreditCard(userId, requestDTO);

        StandardResponse<CreditCardResponseDTO> response = StandardResponse.<CreditCardResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Credit card updated successfully")
                .data(updatedCard)
                .build();

        return ResponseEntity.ok(response);
    }
}