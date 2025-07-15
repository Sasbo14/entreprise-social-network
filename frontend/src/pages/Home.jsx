import { useAuthStore } from "../auth/useAuthStore";

export default function Home() {
  const logout = useAuthStore((state) => state.logout);
  return (
    <div className="text-center mt-20">
      <h1 className="text-3xl font-bold">Bienvenue sur le réseau social 👋</h1>
      <button
        onClick={logout}
        className="mt-4 bg-red-500 text-white p-2 rounded"
      >
        Déconnexion
      </button>
    </div>
  );
}
