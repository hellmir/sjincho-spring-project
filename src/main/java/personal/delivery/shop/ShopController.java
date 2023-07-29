package personal.delivery.shop;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.delivery.shop.dto.ShopRequestDto;
import personal.delivery.shop.dto.ShopResponseDto;
import personal.delivery.shop.service.ShopServiceImpl;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopServiceImpl shopService;

    @PostMapping()
    public ResponseEntity<ShopResponseDto> createShop(@Valid @RequestBody ShopRequestDto shopDto) {

        ShopResponseDto shopResponseDto = shopService.saveShop(shopDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shopResponseDto.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).headers(headers).body(shopResponseDto);

    }

    @GetMapping()
    public ResponseEntity<List<ShopResponseDto>> getAllShops() {

        List<ShopResponseDto> shopResponseDtoList = shopService.getAllShops();

        return ResponseEntity.status(HttpStatus.OK).body(shopResponseDtoList);

    }

    @GetMapping("searches")
    public ResponseEntity<List<ShopResponseDto>> getSearchedShops(@RequestParam String searchWord) {

        List<ShopResponseDto> shopResponseDtoList = shopService.getDistinctShops(searchWord);

        return ResponseEntity.status(HttpStatus.OK).body(shopResponseDtoList);

    }

}
