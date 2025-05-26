package com.tradinsimulator.tradingsimulator.records;

import java.util.List;

public record Update(String channel, String method, String type, List<Currency> data) {
}
