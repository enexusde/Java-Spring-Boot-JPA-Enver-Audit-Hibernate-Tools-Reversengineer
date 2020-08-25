package de.e_nexus.codereview.audit;

import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TeacherController {
	protected final Random rnd = new SecureRandom();

	@PersistenceContext
	private final EntityManager entityManager = null;

	@PostMapping("teacher")
	public void insert(String name) {
		entityManager.persist(new Teacher(rnd.nextInt(), name));
	}

	@GetMapping("teacher")
	public void list(PrintWriter pw) {
		for (Teacher teacher : entityManager.createQuery("FROM Teacher", Teacher.class).getResultList()) {
			int id = teacher.getId();
			pw.println(String.format(
					"<tbody><input id=tn%s value=\"%s\"><button onclick=save(%s)>Save<button onclick=del(%s)>Del<button onclick=hist(%s)>Hist</tbody>",
					id, teacher.getName(), id, id, id));
		}
	}

	@PostMapping("teacher/{id}/name")
	public void test(@PathVariable int id, @RequestBody String teachersName) {
		Teacher teacher = entityManager.find(Teacher.class, id);
		teacher.setName(teachersName);
		entityManager.persist(teacher);
	}

	@DeleteMapping("teacher/{id}")
	public void delete(@PathVariable int id) {
		entityManager.remove(entityManager.find(Teacher.class, id));
	}
}
