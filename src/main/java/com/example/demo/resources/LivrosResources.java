package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Comentarios;
import com.example.demo.domain.Livro;
import com.example.demo.services.LivrosService;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {
		
		@Autowired
		private LivrosService livrosService;
		

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Livro>> listar() {
			return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
			livro = livrosService.salvar(livro);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(livro.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
			Optional<Livro> livro = livrosService.buscar(id);
			CacheControl cacheControl = CacheControl.maxAge(200, TimeUnit.SECONDS);  // Utilização da cache
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
			livrosService.deletar(id);
			return ResponseEntity.noContent().build(); //garante que corretude do código http
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> deletar(@RequestBody Livro livro,@PathVariable("id") Long id) {
			livro.setId(id);
			livrosService.atualizar(livro);
			return ResponseEntity.noContent().build();
		}
		
		@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
		public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentarios comentario) {
			comentario = livrosService.salvarComentario(livroId, comentario);
			
			// Pega usuário atual utilizando API
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			comentario.setUsuario(auth.getName());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.build().toUri();  // gera url a ser utilizada no header
			
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
		public ResponseEntity<List<Comentarios>> listarComentarios(@PathVariable("id") Long livroId){
			List<Comentarios> comentarios = livrosService.listarComentarios(livroId);
			return ResponseEntity.status(HttpStatus.OK).body(comentarios);
		}
}
