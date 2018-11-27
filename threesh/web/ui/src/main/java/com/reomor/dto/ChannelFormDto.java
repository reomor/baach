package com.reomor.dto;

import com.reomor.core.domain.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelFormDto {
    private String name;
    private String description;
    private String rules;

    public Channel toChannel() {
        return new Channel(null, this.name, this.description, this.rules, null);
    }
}
