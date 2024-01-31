import Sidebar from "@/components/Sidebar";
import './style.css'
import Header from "@/components/Header";

export default function RootLayout({ children}) {
  return (
    <html lang="en">
      <body className="container">
      <Header />
      <div className="main">
          <Sidebar/>
          <section className="col note-viewer" >{children}</section>
      </div>
      </body>
    </html>
  );
}

