package br.com.oisul.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.oisul.spring.model.Person;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.service.usuario.UsuarioService;

@Controller
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	@Autowired(required=true)
	@Qualifier(value="usuarioService")
	public void setUsuarioService(UsuarioService service){
		this.usuarioService = service;
	}
	
	@RequestMapping(value = "/testeUsuario", method = RequestMethod.GET)
	public String listPersons(Model model) {
		System.out.println("passou action");
//		model.addAttribute("person", new Person());
		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}
	
	@RequestMapping(value = "/abrirCadUsuarioSite", method = RequestMethod.GET)
	public String testeBanana(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Rafael");
		usuario.setEmail("rafael.estefano.rosa@gmail.com");
		usuario.setDdd("48");
		usuario.setTelefone("3246-8159");
		
		model.addAttribute("usuario", usuario);
		return "site/cadastro";
	}
	
	//For add and update person both
	@RequestMapping(value= "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@ModelAttribute("usuario") Usuario usuario){
		usuarioService.saveUsuario(usuario);
		return "redirect:/abrirCadUsuarioSite";
	}

	@RequestMapping(value = "/confirmarUsuario", method = RequestMethod.GET)
	public String confirmarUsuario(@RequestParam("idUsuario") String idUsuario) {
		try {
			
//			Usuario usuario = usuarioService.confirmarUsuario(idUsuario);
//			setUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "SUCESSO";
	}

	



}
