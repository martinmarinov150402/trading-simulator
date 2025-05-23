import { ReactNode, useEffect, useRef, useState } from "react";
import { createContext } from "react";
import { KrakenService } from "../services/kraken-service";

export const KrakenServiceContext = createContext<KrakenService | null>(null);

export const KrakenServiceContextProvider = ({ children }: { children: ReactNode[] }) => {

  return (
    <KrakenServiceContext.Provider value={new KrakenService()}>
      {children}
    </KrakenServiceContext.Provider>
  );
}
