package com.protocolo.resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.protocolo.models.Protocolo;
import com.protocolo.repository.ProtocoloRepository;
import com.protocolo.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProtocoloResource {
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	//Retornar todos os valores do protocolo
	@GetMapping("/protocolo")
	public List<Protocolo> getAllProtocolo(){		
		return protocoloRepository.findAll();
	}
	
	//Retornar os valores do protocolo pelo ID
	@GetMapping("/protocolo/{id}")
	public ResponseEntity<Protocolo> getProtcoloId(@PathVariable(value = "id") Long protocoloId) throws ResourceNotFoundException{
		
		Protocolo protocolo = protocoloRepository.findById(protocoloId)
				
		.orElseThrow(() -> new ResourceNotFoundException("Protocolo não encontrado para esse id :: " + protocoloId));
		return ResponseEntity.ok().body(protocolo);		
	}
	
	//Cadstrar os dados do formulário
	@PostMapping("/protocolo")
	public Protocolo cadastroProtocolo(@RequestBody Protocolo protocolo) {
		
		//data/hora atual
		LocalDateTime agora = LocalDateTime.now();
		
		//Fornata Data
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataAgora = formatoData.format(agora);
		
		//Formatando Hora
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaAgora = formatoHora.format(agora);
		
		//Atribuindo as variaveis Data e Hora
		protocolo.setData(dataAgora);
		protocolo.setHora(horaAgora);
		
		//Salvando todos os dados do protocolo
		return protocoloRepository.save(protocolo);
	}

	//Excluir um protocolo
	@DeleteMapping("/protocolo/{id}")
	public String deleteProtocolo(@PathVariable(value = "id") Long protocoloId) throws ResourceNotFoundException{
		//Verificar se o ID existe.
		Protocolo protocolo = protocoloRepository.findById(protocoloId)
		.orElseThrow(() -> new ResourceNotFoundException("Protocolo não encontrado para esse id :: " + protocoloId));
		
		//excluir protocolo pelo ID
		protocoloRepository.delete(protocolo);
		
		return "deleted";
	}
	
	@PutMapping("/protocolo/{id}")
	public ResponseEntity<Protocolo> updateProtocolo(@PathVariable(value = "id") long protocoloId, @RequestBody Protocolo protocoloDetails) throws ResourceNotFoundException{
		//Verificar se o ID existe.
		Protocolo protocolo = protocoloRepository.findById(protocoloId)
		.orElseThrow(() -> new ResourceNotFoundException("Protocolo não encontrado para esse id :: " + protocoloId));		
		
		//Atualizando os dados.
		protocolo.setCodigo_ano(protocoloDetails.getCodigo_ano());
		//protocolo.setData(protocoloDetails.getData());
		//protocolo.setHora(protocoloDetails.getHora());
		protocolo.setResumo(protocoloDetails.getResumo());
		protocolo.setSolicitante(protocoloDetails.getSolicitante());
		
		final Protocolo protocoloUpdate = protocoloRepository.save(protocolo);
		return ResponseEntity.ok(protocoloUpdate);				
	}
	
}
