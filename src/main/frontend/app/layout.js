import Link from "next/link";

export default function RootLayout({children,auth}) {

    return (
        <html>
        <div><Link href="/login"> open the modal</Link></div>
        <div><Link href="/">back to home</Link></div>
        <h1>/app/layout.js</h1>
        {children}
        {auth}
        </html>
    )
}