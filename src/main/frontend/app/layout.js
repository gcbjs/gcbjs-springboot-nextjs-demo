import Sidebar from "../components/Sidebar";
import './style.css'

export default function RootLayout({ children}) {
  return (
    <html lang="en">
      <body className="container">
      <div className="main">
          <Sidebar/>
          <section className="col note-viewer" >{children}</section>
      </div>
      </body>
    </html>
  );
}

