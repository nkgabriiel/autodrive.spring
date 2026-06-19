package com.springboot.autodrive.service;

import com.springboot.autodrive.dto.VeiculoRequestDTO;
import com.springboot.autodrive.dto.VeiculoResponseDTO;
import com.springboot.autodrive.exception.PlacaDuplicadaException;
import com.springboot.autodrive.exception.ResourceNotFoundException;
import com.springboot.autodrive.model.Veiculo;
import com.springboot.autodrive.repository.VeiculoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    public static final Logger log = LoggerFactory.getLogger(VeiculoService.class);

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional
    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO dto) {
        if (veiculoRepository.existsByPlaca(dto.placa())) {
            throw new PlacaDuplicadaException(dto.placa());
        }

        Veiculo veiculo = new Veiculo();
        preencherEntidade(veiculo, dto);

        Veiculo salvo = veiculoRepository.save(veiculo);
        log.info("Veículo cadastrado com sucesso. id={} placa={}", salvo.getId(), salvo.getPlaca());

        return VeiculoResponseDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarTodos() {
        return veiculoRepository.findAll().stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDTO buscarPorId(Long id) {
        Veiculo veiculo = buscarEntidadePorId(id);
        return VeiculoResponseDTO.fromEntity(veiculo);
    }

    @Transactional
    public VeiculoResponseDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo veiculo = buscarEntidadePorId(id);

        veiculoRepository.findByPlaca(dto.placa())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new PlacaDuplicadaException(dto.placa());
                });

        preencherEntidade(veiculo, dto);
        Veiculo atualizado = veiculoRepository.save(veiculo);
        log.info("Veículo atualizado com sucesso. id={}", atualizado.getId());

        return VeiculoResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Veiculo veiculo = buscarEntidadePorId(id);
        veiculoRepository.delete(veiculo);
        log.info("Veículo excluído com sucesso. id={}", id);
    }

    private Veiculo buscarEntidadePorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Veículo não encontrado para o id: " + id));
    }

    private void preencherEntidade(Veiculo veiculo, VeiculoRequestDTO dto) {
        veiculo.setMarca(dto.marca());
        veiculo.setModelo(dto.modelo());
        veiculo.setAnoFabricacao(dto.anoFabricacao());
        veiculo.setAnoModelo(dto.anoModelo());
        veiculo.setPlaca(dto.placa());
        veiculo.setPreco(dto.preco());
        veiculo.setCor(dto.cor());
        veiculo.setDisponivel(dto.disponivel());
    }
}
