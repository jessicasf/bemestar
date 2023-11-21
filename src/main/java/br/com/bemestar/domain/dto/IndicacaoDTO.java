package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Indicacao;
import br.com.bemestar.domain.entity.Profissional;
import br.com.bemestar.domain.repository.ProfissionalRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

public class IndicacaoDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotNull(groups = OnCreate.class)
    private Long profissionalId;

    private ProfissionalDTO profissional;

    public IndicacaoDTO(Long id, ProfissionalDTO profissional) {
        this.id = id;
        this.profissional = profissional;
    }

    public IndicacaoDTO() {
    }

    public static Indicacao toEntity(IndicacaoDTO dto, ProfissionalRepository profissionalRepository) {
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional not found"));
        return new Indicacao(
                profissional
        );
    }

    public static IndicacaoDTO toDTO(Indicacao indicacao) {
        IndicacaoDTO dto = new IndicacaoDTO();
        dto.setId(indicacao.getId());
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setId(indicacao.getProfissional().getId());
        profissionalDTO.setNome(indicacao.getProfissional().getNome());
        profissionalDTO.setEmail(indicacao.getProfissional().getEmail());
        profissionalDTO.setImagem(indicacao.getProfissional().getImagem());
        profissionalDTO.setTelefone(indicacao.getProfissional().getTelefone());
        dto.setProfissional(profissionalDTO);
        return dto;
    }

    public Long getId() {
        return id;
    }

    public IndicacaoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProfissionalDTO getProfissional() {
        return profissional;
    }

    public IndicacaoDTO setProfissional(ProfissionalDTO profissional) {
        this.profissional = profissional;
        return this;
    }

    @JsonIgnore
    public Long getProfissionalId() {
        return profissionalId;
    }

    public IndicacaoDTO setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
        return this;
    }
}