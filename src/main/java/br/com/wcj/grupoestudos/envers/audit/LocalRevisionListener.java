package br.com.wcj.grupoestudos.envers.audit;

import org.hibernate.envers.RevisionListener;

public class LocalRevisionListener implements RevisionListener {

	public LocalRevisionListener() {
	}

	@Override
	public void newRevision(Object rev) {
		if(rev instanceof LocalRevisionEntity) {
			LocalRevisionEntity entity = (LocalRevisionEntity) rev;
			entity.setCpf("29729296839");
			entity.setIdPessoaLogada(1L);
			entity.setTipoPessoa("Empreiteiro");
		}
	}

}
