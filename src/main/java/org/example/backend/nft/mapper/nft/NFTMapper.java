package org.example.backend.nft.mapper.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.nft.NFT;
import org.example.backend.nft.entity.nft.NFTDto;
import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.rarity.NFTRarity;
import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.example.backend.nft.mapper.collection.CollectionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NFTMapper {

    public static NFT toEntity(NFTDto dto) {
        return NFT.builder()
                .id(dto.getId())
                .identifier(dto.getIdentifier())
                .contract(dto.getContract())
                .tokenStandard(dto.getTokenStandard())
                .name(dto.getName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .displayImageUrl(dto.getDisplayImageUrl())
                .displayAnimationUrl(dto.getDisplayAnimationUrl())
                .metadataUrl(dto.getMetadataUrl())
                .openseaUrl(dto.getOpenseaUrl())
                .updatedAt(dto.getUpdatedAt())
                .animationUrl(dto.getAnimationUrl())
                .creator(dto.getCreator())
                .build();
    }

    public static NFTDto toDto(NFT nft) {
        NFTDto dto = new NFTDto();
        dto.setId(nft.getId());
        dto.setAnimationUrl(nft.getAnimationUrl());
        dto.setContract(nft.getContract());
        dto.setCreator(nft.getCreator());
        dto.setDescription(nft.getDescription());
        dto.setDisplayAnimationUrl(nft.getDisplayAnimationUrl());
        dto.setDisplayImageUrl(nft.getDisplayImageUrl());
        dto.setIdentifier(nft.getIdentifier());
        dto.setImageUrl(nft.getImageUrl());
        dto.setIsDisabled(nft.getIsDisabled());
        dto.setIsNsfw(nft.getIsNsfw());
        dto.setIsSuspicious(nft.getIsSuspicious());
        dto.setMetadataUrl(nft.getMetadataUrl());
        dto.setName(nft.getName());
        dto.setOpenseaUrl(nft.getOpenseaUrl());
        dto.setUpdatedAt(nft.getUpdatedAt());
        dto.setTokenStandard(nft.getTokenStandard());
        return dto;
    }



    public static List<NFT> mapJsonResponseToEntities(String jsonResponse, Collection collection) {
        List<NFT> nfts = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode assetsNode = rootNode.path("nfts");

            for (JsonNode node : assetsNode) {
                NFT nft = new NFT();
                nft.setIdentifier(node.path("identifier").asText());
                nft.setCollection(collection);
                nft.setContract(node.path("contract").asText());
                nft.setName(node.path("name").asText());
                nft.setDescription(node.path("description").asText());
                nft.setImageUrl(node.path("image_url").asText());
                nft.setOpenseaUrl(node.path("opensea_url").asText());
                nft.setUpdatedAt(node.path("updated_at").asText());
                nft.setIsDisabled(node.has("is_disabled") && node.path("is_disabled").asBoolean());
                nft.setIsNsfw(node.has("is_nsfw") && node.path("is_nsfw").asBoolean());
                nft.setIsSuspicious(node.has("is_suspicious") && node.path("is_suspicious").asBoolean());

                List<NFTTrait> traits = new ArrayList<>();
                for (JsonNode traitNode : node.path("traits")) {
                    NFTTrait trait = new NFTTrait();
                    trait.setTraitType(traitNode.path("trait_type").asText());
                    trait.setDisplayType(traitNode.path("display_type").asText());
                    trait.setMaxValue(traitNode.path("max_value").asText());
                    trait.setValue(traitNode.path("value").asText());
                    traits.add(trait);
                }
                nft.setTraits(traits);

                List<NFTOwner> owners = new ArrayList<>();
                for (JsonNode ownerNode : node.path("owners")) {
                    NFTOwner owner = new NFTOwner();
                    owner.setAddress(ownerNode.path("address").asText());
                    owner.setQuantity(ownerNode.path("quantity").asInt());
                    owners.add(owner);
                }
                nft.setOwners(owners);

                NFTRarity rarity = new NFTRarity();
                JsonNode rarityNode = node.path("rarity");
                if (rarityNode != null) {
                    rarity.setStrategyVersion(rarityNode.path("strategy_version").asText());
                    rarity.setRank(rarityNode.path("rank").asInt());
                    rarity.setScore(rarityNode.path("score").asDouble());
                    rarity.setCalculatedAt(rarityNode.path("calculated_at").asText());
                    rarity.setMaxRank(rarityNode.path("max_rank").asInt());
                    rarity.setTotalSupply(rarityNode.path("total_supply").asInt());
                    rarity.setUniqueAttributeCount(rarityNode.path("ranking_features").path("unique_attribute_count").asInt());
                    nft.setRarity(rarity);
                }

                nfts.add(nft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nfts;
    }

}
