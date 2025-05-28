import { useEffect, useState } from 'react';
import { useAsync } from '../../hooks/use-async';
import { httpService } from '../../services/http-service';
import './table-style.css'

export function LogsPage() {

    type Transaction = {
        ammount: number,
        currency: string,
        createdAt: Date,
        money: number,
    }

    const [data, setData] = useState<Transaction[]>([]);

    useAsync(async () => httpService.get<Transaction[]>("/api/transactions/")
    , setData)

    useEffect(() => {
        console.log(data);
    }, [data])
    return (
        <table>
            <thead>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Currency</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Balance Affection</th>
                </tr>
            </thead>
            <tbody>
                {data.map(transaction => (
                    <tr>
                        <td>{`${transaction.createdAt}`}</td>
                        <td>{transaction.currency}</td>
                        <td>{transaction.ammount}</td>
                        <td>{transaction.money}</td>
                    </tr>
            ))}
            </tbody>
        </table>
    );
}