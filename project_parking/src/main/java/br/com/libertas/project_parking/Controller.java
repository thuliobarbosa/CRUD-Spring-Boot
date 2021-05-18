package br.com.libertas.project_parking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.libertas.project_parking.dao.VeiculoRepository;
import br.com.libertas.project_parking.model.Veiculo;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class Controller {
	
	@Autowired
	private VeiculoRepository _veiculoRepository;
	
	@RequestMapping(value = "/veiculo", method = RequestMethod.GET)
	public List<Veiculo> Get() {
		return  _veiculoRepository.findAll();
	}
	
	@RequestMapping(value = "/veiculo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Veiculo> GetById(@PathVariable(value = "id") long id) {
		Optional<Veiculo> veiculo = _veiculoRepository.findById(id);
		if (veiculo.isPresent()) {
			return new ResponseEntity<Veiculo>(veiculo.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/veiculo", method = RequestMethod.POST)
	public Veiculo Post(@Validated @RequestBody Veiculo veiculo) {
		return _veiculoRepository.save(veiculo);
	}
	
	@RequestMapping(value = "/veiculo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Veiculo> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Veiculo newVeiculo) {
		Optional<Veiculo> oldVeiculo = _veiculoRepository.findById(id);
		if (oldVeiculo.isPresent()) {
			Veiculo veic = oldVeiculo.get();
			veic.setPlaca(newVeiculo.getPlaca());
			veic.setModelo(newVeiculo.getModelo());
			veic.setMarca(newVeiculo.getMarca());
			veic.setAno_fabricacao(newVeiculo.getAno_fabricacao());
			veic.setValor(newVeiculo.getValor());
			_veiculoRepository.save(veic);
			return new ResponseEntity<Veiculo>(veic, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/veiculo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> PutAgendamento(@PathVariable(value = "id") long id) {
		
		Optional<Veiculo> veiculo = _veiculoRepository.findById(id);
		if (veiculo.isPresent()) {
			_veiculoRepository.delete(veiculo.get());
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String get() {
		return "Olá mundo";
	}

}


