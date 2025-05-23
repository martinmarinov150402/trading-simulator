import { useContext, useState } from "react";
import { KrakenServiceContext } from "../../contexts/kraken-service-context";

import styles from './styles.module.css'

import './table-style.css'

export function DashboardPage() {
   
    const [data, setData] = useState<{[key: string]: any}>({});

    const krakenService = useContext(KrakenServiceContext);

    function observer(event: MessageEvent) {
        const eventData : {
            channel: string,
            type: string,
            data: [{
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
            }];
        } = JSON.parse(event.data);
        if(eventData.channel === 'ticker' && eventData.type === 'update') {
            setData(oldData => ({
                ...oldData,
                [eventData.data[0].symbol] : eventData.data[0]
                
            }))
        }
    }

    krakenService?.addObserver(observer);

    return (
        <table> 
            <thead>
                <tr>
                    <th scope="col">Currency Pair</th>
                    <th scope="col">Ask Price</th>
                    <th scope="col">Ask Quantity</th>
                    <th scope="col">Bid Price</th>
                    <th scope="col">Bid Quantity</th>
                </tr>
            </thead>
            <tbody>
                {Object.keys(data).map(key => (
                    <tr key={key}>
                        <td key={`${key}Symbol`}> {data[key].symbol} </td>
                        <td key={`${key}Ask`}> {data[key].ask} </td>
                        <td key={`${key}AskQ`}> {data[key].ask_qty} </td>
                        <td key={`${key}Bid`}> {data[key].bid} </td>
                        <td key={`${key}BidQ`}> {data[key].bid_qty} </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}