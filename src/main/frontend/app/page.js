

export default function Page() {
    console.log(".env.local",process.env.DB_HOST)
  return (
    <div className="note--empty-state" >
      <span className="note-text--empty-state">
        Click a note on the left to view something! 🥺
      </span>
    </div>
  );
}
