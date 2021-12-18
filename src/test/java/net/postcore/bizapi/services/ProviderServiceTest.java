package net.postcore.bizapi.services;

import net.postcore.bizapi.api.v1.mapper.ProviderMapper;
import net.postcore.bizapi.api.v1.model.ProviderDTO;
import net.postcore.bizapi.api.v1.model.ProviderListDTO;
import net.postcore.bizapi.domain.Provider;
import net.postcore.bizapi.repositories.ProviderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


public class ProviderServiceTest {

    public static final Long ID_1 = 1L;
    public static final String NAME_1 = "Acme Services";
    public static final Long ID_2 = 2L;
    public static final String NAME_2 = "Smith Janitorial";
    ProviderService providerService;

    @Mock
    ProviderRepository providerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        providerService = new ProviderServiceImpl(ProviderMapper.getInstance(), providerRepository);
    }

    @Test
    public void getAllProviders() {
        // arrange
        List<Provider> providers = Arrays.asList(getProvider1(), getProvider2());
        given(providerRepository.findAll()).willReturn(providers);

        // act
        ProviderListDTO providerListDTO = providerService.getAllProviders();

        // assert
        then(providerRepository).should(times(1)).findAll();
        assertThat(providerListDTO.getProviders().size(), is(equalTo(2)));
    }

    @Test
    public void getProviderById() {
        Provider provider = getProvider1();
        given(providerRepository.findById(anyLong())).willReturn(Optional.of(provider));
        ProviderDTO providerDTO = providerService.getProviderById(1L);
        then(providerRepository).should(times(1)).findById(anyLong());

        assertThat(providerDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getProviderByIdNotFound() throws Exception {
        //given
        //mockito BBD syntax since mockito 1.10.0
        given(providerRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        ProviderDTO providerDTO = providerService.getProviderById(1L);

        //then
        then(providerRepository).should(times(1)).findById(anyLong());

    }
    
    @Test
    public void createNewProvider() throws Exception {
        //given
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName(NAME_1);

        Provider provider = getProvider1();

        given(providerRepository.save(any(Provider.class))).willReturn(provider);

        //when
        ProviderDTO savedProviderDTO = providerService.createNewProvider(providerDTO);

        //then
        // 'should' defaults to times = 1
        then(providerRepository).should().save(any(Provider.class));
        assertEquals(savedProviderDTO.getName(), NAME_1);
    }

    @Test
    public void saveProviderByDTO() throws Exception {
        // arrange
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName("Cat's Catering");

        Provider savedProvider = new Provider();
        savedProvider.setName(providerDTO.getName());
        savedProvider.setId(1L);

        when(providerRepository.save(any(Provider.class))).thenReturn(savedProvider);

        // act
        ProviderDTO savedDto = providerService.saveProviderByDTO(1L, providerDTO);

        // assert
        assertEquals(providerDTO.getName(), savedDto.getName());
    }

    @Test
    public void deleteProviderById() throws Exception {
        // arrange
        Long id = 1L;

        // act
        providerService.deleteProviderById(id);

        // assert
        verify(providerRepository, times(1)).deleteById(anyLong());
    }    

    private Provider getProvider1() {
        Provider provider = new Provider();
        provider.setName(NAME_1);
        provider.setId(ID_1);
        return provider;
    }

    private Provider getProvider2() {
        Provider provider = new Provider();
        provider.setName(NAME_2);
        provider.setId(ID_2);
        return provider;
    }    
}
