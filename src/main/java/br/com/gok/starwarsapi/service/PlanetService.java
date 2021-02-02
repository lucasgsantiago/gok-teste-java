package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.exception.NotFoundException;
import br.com.gok.starwarsapi.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService implements IPlanetService {
    private final IPlanetRepository repository;

    private PlanetMapper mapper = PlanetMapper.INSTANCE;

    @Override
    public PageResponse<PlanetDTO> listAll(Pageable pageable) {
        return createPageResponse(repository.getAll(pageable));
    }

    @Override
    public PlanetDTO findByName(String name) {
        return repository.findByName(name).map(mapper::toPresenter).orElseThrow(() -> new NotFoundException(Constants.PLANET_NOT_FOUND));
    }

    @Override
    public PlanetDTO findById(Long id) {
        return repository.findById(id).map(mapper::toPresenter).orElseThrow(() -> new NotFoundException(Constants.PLANET_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Planet planet = repository.findById(id).orElseThrow(() -> new NotFoundException(Constants.PLANET_NOT_FOUND));
        repository.delete(planet);
    }

    @Override
    public PageResponse<PlanetDTO> filterByQuery(String query, Pageable pageable) {
        SearchCriteria criteria = SearchCriteriaFactory.createSearchCriteria(query);
        Page<Planet> page =  repository.filterByQuery(criteria,pageable);
        return createPageResponse(page);
    }

    private PageResponse<PlanetDTO> createPageResponse(Page page){
        return new PageResponse<PlanetDTO>(page.getSize(),page.getTotalPages(),page.getNumber(),page.getTotalElements(),mapper.toPresenter(page.getContent()));
    }

}
