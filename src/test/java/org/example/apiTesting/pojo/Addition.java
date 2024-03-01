package org.example.apiTesting.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Addition {
    private Integer id;

    @SerializedName("additional_info")
    private String additionalInfo;

    @SerializedName("additional_number")
    private int additionalNumber;
}
