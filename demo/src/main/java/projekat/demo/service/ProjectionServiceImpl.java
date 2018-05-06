package projekat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.demo.model.Projection;
import projekat.demo.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public Projection createProjection(Projection p) {
		
		Projection findProj = this.projectionRepository.findByNameAndPlace(p.getName(), p.getPlace());
		if (findProj == null) {
			return this.projectionRepository.save(p);
		}
		
		return null;
	}

	@Override
	public Projection updateProjection(Projection p) {
		
		return this.projectionRepository.save(p);
	}

	@Override
	public boolean deleteProjection(Projection p) {
		
		if (p == null) {
			return false;
		}
		
		Projection delProj = this.projectionRepository.findByNameAndPlace(p.getName(), p.getPlace());
		
		if (delProj == null) {
			return false;
		}
	
		this.projectionRepository.delete(p);
				
		return true;
	}

}
