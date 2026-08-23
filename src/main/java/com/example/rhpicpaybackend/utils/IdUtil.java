package com.example.rhpicpaybackend.utils;

import com.example.rhpicpaybackend.model.Funcionario;
import lombok.NonNull;

import java.util.List;

public class IdUtil {

    public static Long gerarId(@NonNull List<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            return 1L;
        }

        return funcionarios.getLast().getId() + 1;
    }
}
