package org.example.apiTesting.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class EntityPojo {
    private Integer id;
    @SerializedName("addition")
    private Addition addition;

    @SerializedName("important_numbers")
    private List<Integer> importantNumbers;

    @SerializedName("title")
    private String title;

    @SerializedName("verified")
    private Boolean verified;
}
