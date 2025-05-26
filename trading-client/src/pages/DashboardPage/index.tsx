import { useContext, useEffect, useState } from "react";
import { KrakenServiceContext } from "../../contexts/kraken-service-context";

import styles from './styles.module.css'

import './table-style.css'
import { authService } from "../../services/auth";
import { useNavigate } from "react-router";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import { faDollarSign } from "@fortawesome/free-solid-svg-icons";
import { httpService } from "../../services/http-service";

export function DashboardPage() {
   
    const [data, setData] = useState<{[key: string]: any}>({});

    const krakenService = useContext(KrakenServiceContext);

    const navigate = useNavigate();

    useEffect(() => {
        if(!authService.currentUser) {
            navigate("/");
        }
    }, [navigate]);

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
                    <th scope="col">Buy</th>
                    <th scope="col">Sell</th>
                </tr>
            </thead>
            <tbody>
                {Object.keys(data).map(key => (
                    <>
                        <tr key={key}>
                            <td key={`${key}Symbol`}> {data[key].symbol} </td>
                            <td key={`${key}Ask`}> {data[key].ask} </td>
                            <td key={`${key}AskQ`}> {data[key].ask_qty} </td>
                            <td key={`${key}Bid`}> {data[key].bid} </td>
                            <td key={`${key}BidQ`}> {data[key].bid_qty} </td>
                            <td key={`${key}Buy`}><FontAwesomeIcon key={`${key}BIcon`} onClick={async () => {
                                let ammount = 0;
                                do
                                {
                                    const inputValue = parseFloat(prompt(`Enter how much of ${data[key].symbol} you want to buy?`) ?? "0");
                                    if(!isNaN(inputValue)) {
                                        ammount = inputValue;
                                    }
                                    
                                }
                                while(ammount <= 0)

                                const result = await httpService.post<{ammount: number}>("/api/currency/buy", {
                                    currency: data[key].symbol,
                                    ammount,
                                })

                                if(result && result.ammount) {
                                    alert(`Operation was successful now you have ${result.ammount} ${data[key].symbol}`)
                                } else {
                                    alert("Operation wasn't successful maybe due to insufficient balance!")
                                }
                                authService.refreshUser();
                                navigate("/");
                                
                                

                            }} icon={faCartShopping}/></td>
                            <td key={`${key}Sell`}><FontAwesomeIcon key={`${key}SIcon`} onClick={async () => {
                                let ammount = 0;
                                do
                                {
                                    const inputValue = parseFloat(prompt(`Enter how much of ${data[key].symbol} you want to sell?`) ?? "0");
                                    if(!isNaN(inputValue)) {
                                        ammount = inputValue;
                                    }
                                    
                                }
                                while(ammount <= 0)
                                
                                const result = await httpService.post<{ammount: number}>("/api/currency/sell", {
                                    currency: data[key].symbol,
                                    ammount,
                                })

                                if(result && result.ammount) {
                                    alert(`Operation was successful now you have ${result.ammount} ${data[key].symbol}`)
                                }
                                else {
                                    alert("Operation wasn't successful maybe due to insufficient ammount!");
                                }
                                authService.refreshUser();
                                navigate("/")
                                

                            }} icon={faDollarSign}/></td>
                        </tr>
                    </>
                ))}
            </tbody>
        </table>
    );
}