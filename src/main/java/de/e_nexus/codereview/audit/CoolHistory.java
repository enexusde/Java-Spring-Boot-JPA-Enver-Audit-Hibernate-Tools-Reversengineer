package de.e_nexus.codereview.audit;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class CoolHistory {

	@PersistenceContext
	private final EntityManager entityManager = null;

	@GetMapping("teacher/{id}/history")
	public void history(@PathVariable int id, PrintWriter out) {
		AuditReader r = AuditReaderFactory.get(entityManager);
		for (Number teachersRevision : r.getRevisions(Teacher.class, id)) {
			Teacher teacher = r.find(Teacher.class, id, teachersRevision);
			out.print("Changed name to ");
			out.print(teacher.getName());
			out.print(" ");
			out.println(when(teachersRevision));
		}
	}

	private String when(Number teachersRevision) {
		AuditReader r = AuditReaderFactory.get(entityManager);
		SimpleDateFormat timeInBracets = new SimpleDateFormat("(hh:mm)");
		Date changed = r.getRevisionDate(teachersRevision);
		String format = timeInBracets.format(changed);
		return format;
	}
}
