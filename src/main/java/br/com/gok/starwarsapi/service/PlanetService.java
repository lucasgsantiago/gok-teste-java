package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import br.com.gok.starwarsapi.util.PlanetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService implements IPlanetService {
    private final IPlanetRepository repository;
    private final IUpdatePlanetsFromSwapiService updatePlanetsFromSwapiService;

    private PlanetMapper mapper = PlanetMapper.INSTANCE;

    @Override
    public PageResponse<PlanetDTO> listAll(Pageable pageable) {
        Page<Planet> page = repository.getAll(pageable);

        if(page.isEmpty()){
            page = updatePlanetsFromSwapiService.execute(pageable);
        }

        return new PageResponse<>(page.getSize(),page.getTotalPages(),page.getNumber(),page.getTotalElements(),mapper.toPresenter(page.getContent()));
    }

    @Override
    public List<PlanetDTO> filterByPopulation() {
        return null;
    }

    @Override
    public PlanetDTO findByName(String name) {
        return mapper.toPresenter(repository.getByName(name));
    }

    @Override
    public PlanetDTO findById(Long id) {
        return mapper.toPresenter(repository.getById(id));
    }

    @Override
    public void remove(Long id) {
        repository.delete(new Planet().builder().id(id).build());
    }
}
