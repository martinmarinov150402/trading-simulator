package com.tradinsimulator.tradingsimulator.records;

/*
ask: number,
                ask_qty: number,
                bid: number,
                bid_qty: number,
                change: number,
                change_pct: number,
                high: number,
                last: number,
                low: number,
                symbol: string,
                volume: number,
                vwap: number
 */
public record Currency (String symbol,
                        Double ask,
                        Double ask_qty,
                        Double bid,
                        Double bid_qty,
                        Double change,
                        Double change_pct,
                        Double high,
                        Double last,
                        Double low,
                        Double volume,
                        Double vwap) {
}
