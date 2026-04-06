import plotly.graph_objects as go
import pandas as pd

df = pd.read_csv('data-IF-CC.csv')

#print(df)
#print(df['LabelColor'])

# override gray link colors with 'source' colors
opacity = 0.4
# change 'magenta' to its 'rgba' value to add opacity
df['NodeColor'] = ['rgba(255,0,255, 0.8)' if color == "magenta" else color for color in df['NodeColor']]
df['LinkColor'] = [df['NodeColor'][src].replace("0.8", str(opacity)) for src in df['Source']] 

fig = go.Figure(data=[go.Sankey(
    node = dict(
      pad = 15,
      thickness = 20,
      line = dict(color = "black", width = 0.5),
      label = df['Label'],
      color = df['NodeColor']
    ),
    link = dict(
      source = df['Source'], # indices correspond to labels
      target = df['Target'],
      value = df['Value'],
      color = df['LinkColor']
  ))])

fig.update_layout(title_text="IF-CC", font_size=40)
fig.show()