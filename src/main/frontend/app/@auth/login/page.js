'use client'
import { useRouter } from 'next/navigation'


export default function Page() {
    const router = useRouter();

    return (
        <div style={{width:'300px',height:'200px',backgroundColor:'red',position:'fixed',top:'20px',left:'700px'}}>
            <span onClick={() =>router.back()}>close Modal</span>
            <h1>Modal Content</h1>
        </div>
    )
}