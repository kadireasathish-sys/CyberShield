import { useEffect, useState } from 'react';
import { api } from '../services/api';
import { AlertTriangle, ShieldCheck, Clock3, Users, ArrowUpRight } from 'lucide-react';
import { ResponsiveContainer, BarChart, Bar, XAxis, YAxis, Tooltip } from 'recharts';
import Badge from '../components/Badge';

export default function Dashboard() {
  const [data, setData] = useState(null);
  const [error, setError] = useState('');
  useEffect(() => {
    api('/dashboard').then(setData).catch((e) => setError(e.message || 'Could not load dashboard'));
  }, []);

  if (error) return <div className="error" role="alert">{error}</div>;
  if (!data) return <div className="loading">Loading security telemetry…</div>;

  const cards = [
    ['Total incidents', data.total, AlertTriangle, ''],
    ['Open', data.open, Clock3, 'amber'],
    ['Investigating', data.investigating, ShieldCheck, 'blue'],
    ['Resolved', data.resolved, ShieldCheck, 'green'],
    ...(data.users === undefined ? [] : [['Users', data.users, Users, 'purple']]),
    ['Critical', data.critical, AlertTriangle, 'red'],
  ];
  const chart = [
    { name: 'Open', value: data.open },
    { name: 'Investigating', value: data.investigating },
    { name: 'Resolved', value: data.resolved },
  ];

  return <>
    <div className="grid6">{cards.map(([name, value, Icon, color]) => <div className="stat" key={name}>
      <div className={`statIcon ${color}`}><Icon size={19}/></div><span>{name}</span><strong>{value}</strong><small>Current count</small>
    </div>)}</div>
    <div className="dashGrid">
      <div className="panel"><div className="panelHead"><div><h3>Incident status</h3><p>Response workload for incidents you can access</p></div></div>
        <div className="chart"><ResponsiveContainer width="100%" height={260}><BarChart data={chart}><XAxis dataKey="name"/><YAxis allowDecimals={false}/><Tooltip/><Bar dataKey="value" radius={[6,6,0,0]}/></BarChart></ResponsiveContainer></div>
      </div>
      <div className="panel"><div className="panelHead"><div><h3>Recent incidents</h3><p>Latest incidents you can access</p></div><a href="/incidents">View all <ArrowUpRight size={15}/></a></div>
        <div className="list">{data.recentIncidents.map(incident => <div className="listRow" key={incident.id}><div><b>{incident.title}</b><span>#{incident.id} · {incident.incidentType}</span></div><Badge>{incident.severity}</Badge></div>)}</div>
      </div>
    </div>
  </>;
}
