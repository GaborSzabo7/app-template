package hu.gaszabo.template.adapter.rest;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.gaszabo.template.infrastructure.web.controller.rest.BaseRestController;

@RestController
@RequestMapping("/ping")
public class PingRestController extends BaseRestController {

	@GetMapping
	public Callable<ResponseEntity<Void>> ping() {
		return () -> ResponseEntity.ok().build();
	}

}
