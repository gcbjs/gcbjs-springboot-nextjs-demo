
import Image from "next/image";
import styles from "./page.module.css";
import {GET} from "./api/route";

export default function Home() {

  const data = GET();
  return (
    <h1>{data}</h1>
  );
}
