package pl.effectivedev.bff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@ToString
@Getter
public class Sources {
    private List<String> notes;
    private List<String> articles;
    private List<String> webLinks;
}
