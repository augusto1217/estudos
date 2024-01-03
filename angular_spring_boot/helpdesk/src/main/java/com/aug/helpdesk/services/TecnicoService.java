package com.aug.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.helpdesk.domain.Pessoa;
import com.aug.helpdesk.domain.Tecnico;
import com.aug.helpdesk.domain.dtos.TecnicoDTO;
import com.aug.helpdesk.repositories.PessoaRepository;
import com.aug.helpdesk.repositories.TecnicoRepository;
import com.aug.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.aug.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " +id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null);
        validaPorCpfEmail(objDto);
        return repository.save(new Tecnico(objDto));
    }

    public Tecnico update(Integer id, TecnicoDTO objTecnicoDTO) {
        objTecnicoDTO.setId(id);
        Tecnico oldTecnico = findById(id);
        validaPorCpfEmail(objTecnicoDTO);
        oldTecnico = new Tecnico(objTecnicoDTO);
        return repository.save(oldTecnico);        
    } 

    private void validaPorCpfEmail(TecnicoDTO objDto) {
        
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Cpf já cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }

    }  

}
