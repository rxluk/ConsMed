package com.app.consMed.Modules.Medico.Enums;

public enum Especialidade {
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    PEDIATRIA("Pediatria"),
    ORTOPEDIA("Ortopedia"),
    GINECOLOGIA("Ginecologia"),
    NEUROLOGIA("Neurologia"),
    PSIQUIATRIA("Psiquiatria"),
    UROLOGIA("Urologia"),
    OFTALMOLOGIA("Oftalmologia"),
    RADIOLOGIA("Radiologia");

    private final String descricao;

    Especialidade(String descricao) {
            this.descricao = descricao;
    }

    public String getEspecialidade() {
        return descricao;
    }
}
